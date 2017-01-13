/*
 * CLIF is a Load Injection Framework
 * Copyright (C) 2012 France Telecom R&D
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Contact: clif@ow2.org
 */
package jenkins.model;

import org.junit.Test;
import hudson.model.FreeStyleProject;
import hudson.model.Item;
import hudson.model.ItemGroup;
import static org.fest.assertions.Assertions.assertThat;

public class FakeTest {

	@Test
	public void canFakeAndResetGlobals() {
		assertThat(Jenkins.getInstance()).isNull();
		Jenkins jenkins = Fake.install();

		assertThat(jenkins).isNotNull();
		assertThat(Jenkins.getInstance()).isEqualTo(jenkins);

		Jenkins j = Fake.uninstall();
		assertThat(j).isNull();
		assertThat(Jenkins.getInstance()).isNull();
	}


	public void canThenCreateFreestyleProject() {
		Jenkins jenkins = Fake.install();
		try {
			FreeStyleProject project = new FreeStyleProject(
					(ItemGroup<? extends Item>) jenkins.getItemGroup(),
					"bar"
			);
			assertThat(project.getParent()).isEqualTo(jenkins);
		}
		finally {
			Fake.uninstall();
		}
	}


}
