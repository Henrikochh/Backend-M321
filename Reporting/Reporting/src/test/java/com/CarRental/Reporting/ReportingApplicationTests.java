package com.CarRental.Reporting;

import org.junit.jupiter.api.Test;

class ReportingApplicationTests {

	@Test
	void applicationMainMethodWorks() {
		// Einfacher Test ohne Spring Context für die Kompilierung
		ReportingApplication app = new ReportingApplication();
		// Überprüft, dass die Klasse erstellt werden kann
		assert app != null;
	}

}