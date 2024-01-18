package com.nagarro.ProductSearchApi.model;


public class Views {
	/*
	 * View interfaces for controlling serialization of fields in models
	 * It provides separate views for public and authenticated scenarios.
	 * */
    public interface Public {}
    public interface Authenticated extends Public {}
}
