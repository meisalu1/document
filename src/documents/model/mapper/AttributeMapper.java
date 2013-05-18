package documents.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import documents.classes.GeneralFunction;
import documents.classes.MyLogger;
import documents.classes.StructureMapper;
import documents.model.data.Attribute;
import documents.model.data.Selection;

public class AttributeMapper implements StructureMapper<Attribute[]>{
	
	GeneralFunction function = new GeneralFunction();

	@Override
	public Attribute[] mapSet(ResultSet resultSet) throws SQLException {
		
		Attribute[] attributes = null;
		Selection[] options = null;
		Queue<Attribute> attributeList = new LinkedList<Attribute>();
		Queue<Selection> optionsList = new LinkedList<Selection>();
		Selection option;
		int counter = 0;
		Attribute attribute = null;
		
		while(resultSet.next()) {	
			
			if(attribute == null || attribute.getAttribute_type() != resultSet.getInt("doc_attribute_type")){
				
				if(attribute != null && attribute.getData_type() == 4){
					options = new Selection[optionsList.size()];
					
					while(!optionsList.isEmpty()){
						options[counter] = optionsList.poll();
						counter++;
					}

					attribute.setSelections(options);
					counter = 0;
				}
				
				if(attribute != null)
					attributeList.add(attribute);
				
				attribute = new Attribute();
				
				attribute.setName(resultSet.getString("type_name"));
				attribute.setReq((resultSet.getString("required")).equals("Y") ? true : false);
				attribute.setAttribute_type(resultSet.getInt("doc_attribute_type"));
				attribute.setOrderby(resultSet.getInt("orderby"));
				attribute.setDefaultValue(function.stringIsSet(resultSet.getString("default_value_text"), ""));
				attribute.setData_type(resultSet.getInt("data_type_fk"));
				
					
				if (attribute.getData_type() == 4) {
					attribute.setDefaultValue(resultSet.getString("valiku_id"));
					option = new Selection();
					option.setId(resultSet.getInt("atr_type_selection_value"));
					option.setName(resultSet.getString("value_text"));
					optionsList.add(option);
				}
			} else {
				option = new Selection();
				option.setId(resultSet.getInt("atr_type_selection_value"));
				option.setName(resultSet.getString("value_text"));
				optionsList.add(option);
				
				if(resultSet.isLast()) {
					options = new Selection[optionsList.size()];
					
					while(!optionsList.isEmpty()){
						options[counter] = optionsList.poll();
						counter++;
					}

					attribute.setSelections(options);
					counter = 0;
				}
			}
			if(resultSet.isLast()){
				attributeList.add(attribute);
			}
		}	
		
		attributes = new Attribute[attributeList.size()];
		
		while(!attributeList.isEmpty()){
			attributes[counter] = attributeList.poll();
			counter++;
		}
		
		MyLogger.LogMessage("mapper attrs size  " + attributes.length);
		
		return attributes;
	}
}
