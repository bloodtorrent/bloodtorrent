package org.bloodtorrent.view;

import lombok.Getter;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/25/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainView extends CommonView {

    @Getter
    private List<SuccessStory> successStories;

    public MainView(User user, List<SuccessStory> successStories) {
        super("/ftl/main.ftl", user);
        this.successStories = successStories;
    }

    public MainView(List<SuccessStory> successStories) {
        super("/ftl/main.ftl");
        this.successStories = successStories;
    }

}
