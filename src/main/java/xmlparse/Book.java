package xmlparse;

//https://blog.csdn.net/zflovecf/article/details/78908788#

import lombok.Data;

@Data
public class Book {
	private int id;
	private String name;
	private String author;
	private int year;
	private double price;
}
