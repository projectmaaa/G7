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
	 *            The name of the file
	 */
	public MyFile(String fileName) {
		this.fileName = fileName;
	}

	/******************** Getters and Setters ********************/

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
	 *            The name of the file
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
	 *            The size of the byte array
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
	 *            Index
	 * @return The element at the i index
	 */
	public byte getMybytearray(int i) {
		return mybytearray[i];
	}

	/**
	 * 
	 * @param mybytearray
	 *            The Byte Array
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
	 *            The file description
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/******************** Methods ********************/

	/**
	 * Allocates the byte array by size
	 * 
	 * @param size
	 *            The wished size
	 */
	public void initArray(int size) {
		mybytearray = new byte[size];
	}

} // end of class