package com.jdm.garam.util
/*
import android.util.Xml
import com.google.gson.GsonBuilder
import com.squareup.moshi.Json
import okhttp3.ResponseBody
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.lang.reflect.Type

internal class XmlOrJsonConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<Annotation>,
        retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation.annotationClass == Xml::class.java) {
                return SimpleXmlConverterFactory.createNonStrict(
                    Persister(AnnotationStrategy())
                ).responseBodyConverter(type, annotations, retrofit)
            }
            if (annotation.annotationClass == Json::class.java) {
                return GsonConverterFactory.create(
                    GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()
                ).responseBodyConverter(type, annotations, retrofit)
            }
        }
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()
        ).responseBodyConverter(type, annotations, retrofit)
    }
}

 */