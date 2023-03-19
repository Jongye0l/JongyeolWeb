package com.Jongyeol.JongyeolWeb.hcec.Wiki;

public interface WikiPageRepository {
    WikiData save(WikiData page);
    WikiData findByTitle(String title);
}
