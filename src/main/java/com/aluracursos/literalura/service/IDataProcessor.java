package com.aluracursos.literalura.service;

public interface IDataProcessor {

    <T> T obtainData(String json, Class<T> tClass);
}
