/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.manchester.cs.owl.owlapi;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFactory;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 15-Nov-2006<br><br>
 */
public abstract class AbstractInMemOWLOntologyFactory implements OWLOntologyFactory {


	private static final long serialVersionUID = 30402L;
	private OWLOntologyManager ontologyManager;

    @Override
    public void setOWLOntologyManager(OWLOntologyManager owlOntologyManager) {
    	if (owlOntologyManager == null) {
            throw new IllegalArgumentException("ontologyManager cannot be null");
        }
        ontologyManager = owlOntologyManager;
    }


    @Override
    public OWLOntologyManager getOWLOntologyManager() {
        return ontologyManager;
    }


    @Override
    public boolean canCreateFromDocumentIRI(IRI documentIRI) {
        return true;
    }

    /**
     * Creates an empty ontology that a concrete representation can be
     * parsed into.  Subclasses can override this method to change the implementation
     * of the ontology.
     * @param documentIRI the document IRI
     * @param ontologyID the ontology id
     * @param handler the creation handler
     */

    @Override
    public OWLOntology createOWLOntology(OWLOntologyID ontologyID, IRI documentIRI, OWLOntologyCreationHandler handler) throws OWLOntologyCreationException {
        OWLOntology ont = new OWLOntologyImpl(ontologyManager, ontologyID);

        handler.ontologyCreated(ont);
        return ont;
    }
}
