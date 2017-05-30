/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.entity.converter;

import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author zrobe
 */
@Converter
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {
	
    @Override
    public Time convertToDatabaseColumn(LocalTime locTime) {
    	return (locTime == null ? null : Time.valueOf(locTime));
    }

    @Override
    public LocalTime convertToEntityAttribute(Time sqlTime) {
    	return (sqlTime == null ? null : sqlTime.toLocalTime());
    }
}
