package org.bloodtorrent.bootstrap;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.jaxrs.json.JsonMappingExceptionMapper;
import com.sun.jersey.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 4/1/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class BloodTorrentCustom404 implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity("YOU FAIL.").type("text/plain").build();
    }

}
