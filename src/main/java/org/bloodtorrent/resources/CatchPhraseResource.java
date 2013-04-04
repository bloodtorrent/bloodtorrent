package org.bloodtorrent.resources;

import org.bloodtorrent.dto.CatchPhrase;
import org.bloodtorrent.repository.CatchPhraseRepository;

public class CatchPhraseResource {

    private CatchPhraseRepository repository;

    protected CatchPhraseResource(CatchPhraseRepository repository) {
        this.repository = repository;
    }


    public CatchPhrase get() {
        return repository.get();
    }
}
