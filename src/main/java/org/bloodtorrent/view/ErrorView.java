package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;

import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 3:20
 * To change this template use File | Settings | File Templates.
 */
public class ErrorView extends View {
    private boolean isNullPointerException = false;
    private String field = "";
    public ErrorView(String viewPath) {
        super(viewPath);
    }
    public ErrorView(String viewPath, String field) {
        super(viewPath);
        setField(field);
    }

    public void setField(String field){
        this.field = field;
    }

    public String getField(){
        return field;
    }

    public boolean getIsNullPointerException(){
        return this.isNullPointerException;
    }

    public void setIsNullPointerException(boolean isNullPointerException){
        this.isNullPointerException = isNullPointerException;
    }
}
