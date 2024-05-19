package com.company.inventory.response;
import java.util.List;
import com.company.inventory.model.category;
import lombok.Data;

@Data
public class categoryResponse {
	private List<category> category;
}