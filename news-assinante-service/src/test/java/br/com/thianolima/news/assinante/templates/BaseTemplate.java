package br.com.thianolima.news.assinante.templates;

import com.github.javafaker.Faker;

import java.util.Locale;

public abstract class BaseTemplate {

    protected final Faker faker = new Faker(new Locale("pt-BR"));
}
