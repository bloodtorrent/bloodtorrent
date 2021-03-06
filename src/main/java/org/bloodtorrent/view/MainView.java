package org.bloodtorrent.view;

import lombok.Getter;
import org.bloodtorrent.dto.CatchPhrase;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;

import java.util.List;

@SuppressWarnings("PMD.UnusedPrivateField")
public class MainView extends CommonView {

    private static final String PATH = "/ftl/main.ftl";

    @Getter
    private List<SuccessStory> successStories;

    @Getter
    private CatchPhrase catchPhrase;

    public MainView(User user, List<SuccessStory> successStories) {
        super(PATH, user);
        this.successStories = successStories;
    }

    public MainView(List<SuccessStory> successStories) {
        super(PATH);
        this.successStories = successStories;
    }

    public MainView(CatchPhrase catchPhrase) {
        super(PATH);
        this.catchPhrase = catchPhrase;
    }

    public MainView(User user, CatchPhrase catchPhrase) {
        super(PATH, user);
        this.catchPhrase = catchPhrase;
    }
}
