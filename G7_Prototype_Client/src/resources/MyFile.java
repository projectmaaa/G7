package resources;

import java.io.Serializable;

/**
 * The class holds information about word files
 * 
 * @author Group 7
 *
 */
public class MyFile implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String Description = null;

	private String fileName = null;

	private int size = 0;

	public byte[] mybytearray;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param fileName
	 */
	public MyFile(String fileName) {
		this.fileName = fileName;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The name of the file
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 
	 * @return The size of the byte array
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 
	 * @return The byte array
	 */
	public byte[] getMybytearray() {
		return mybytearray;
	}

	/**
	 * 
	 * @param i
	 * @return The element at the i index
	 */
	public byte getMybytearray(int i) {
		return mybytearray[i];
	}

	/**
	 * 
	 * @param mybytearray
	 */
	public void setMybytearray(byte[] mybytearray) {
		for (int i = 0; i < mybytearray.length; i++)
			this.mybytearray[i] = mybytearray[i];
	}

	/**
	 * 
	 * @return The file description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/******************** Methods ********************/

	/**
	 * Allocates the byte array by size
	 * 
	 * @param size
	 */
	public void initArray(int size) {
		mybytearray = new byte[size];
	}

} // end of class