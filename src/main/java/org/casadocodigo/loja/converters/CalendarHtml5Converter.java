package org.casadocodigo.loja.converters;

import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Calendar.class)
public class CalendarHtml5Converter implements Converter {

	private static DateTimeConverter converter = new DateTimeConverter();
	
	static{
		converter.setPattern("yyyy-MM-dd");
//		converter.setPattern("MM/dd/yyyy");
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Date date = (Date)converter.getAsObject(context, component, value);
		
		if(date == null){
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null){
			return null;			
		}
		
		Calendar calendar = (Calendar)value;
		return converter.getAsString(context, component, calendar.getTime());
		
	}

}
