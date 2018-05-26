package org.mog.plugin.fe.fichero;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.mog.bo.Ficha;
import org.mog.conf.Utilitario;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/ficha/*")
public class FicheroServlet extends HttpServlet {

    public  FicheroServlet(){
        FrontEndFicheroAdapter frontEndFicheroAdapter = (FrontEndFicheroAdapter) Utilitario.myContainer().find("ficheroAdapter");
        dummyInsert(frontEndFicheroAdapter, "33321188");
        dummyInsert(frontEndFicheroAdapter, "30321188");
        dummyInsert(frontEndFicheroAdapter, "27888999");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FrontEndFicheroAdapter frontEndFicheroAdapter = (FrontEndFicheroAdapter) Utilitario.myContainer().find("ficheroAdapter");

        String requestURI = req.getRequestURI();
        List<String> strings = Arrays.asList(requestURI.split("/"));
        String sid = strings.size()==4 && !strings.isEmpty() ? strings.get(3) : "";


        List<Ficha> fichas = sid.isEmpty() ? frontEndFicheroAdapter.findAll() : frontEndFicheroAdapter.fichasRealizadas(sid);


        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();

        ObjectMapper mapper = getDefaultMapper();
        List<FichaFE> collect = fichas.stream().map(f -> FichaFE.map(f)).collect(Collectors.toList());
        String result = mapper.writeValueAsString(collect);

        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap());
        System.out.println(resp);
        super.doPost(req, resp);
    }

    private ObjectMapper getDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new ISO8601DateFormat());
        return mapper;
    }


    private void dummyInsert(FrontEndFicheroAdapter frontEndFicheroAdapter, String empleado) {
        frontEndFicheroAdapter.ficharIngreso(empleado, "2018-05-23", "10:20");
        frontEndFicheroAdapter.ficharEgreso(empleado, "2018-05-23", "20:20");

        frontEndFicheroAdapter.ficharIngreso(empleado, "2018-05-24", "10:20");
        frontEndFicheroAdapter.ficharEgreso(empleado, "2018-05-24", "20:20");

        frontEndFicheroAdapter.ficharIngreso(empleado, "2018-05-25", "10:20");
        frontEndFicheroAdapter.ficharEgreso(empleado, "2018-05-25", "20:20");
    }
}
