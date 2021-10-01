/*
 * Copyright (c) 2013-2020 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/**
 * Abstract Factory for persistence implementations.
 *
 * <p>
 * The general structure of the persistence implementations is as follows.
 * </p>
 * <p>
 * <img src="sample-repo-structure.svg" alt="sample-repo-structure.svg">
 * </p>
 *
 * <!--
 *
 * @startuml sample-repo-structure.svg
 *
 *           package core.infrastructure.persistence
 *           {
 *           class PersistenceContext
 *
 *           interface RepositoryFactory
 *           {
 *           DishTypeRepository dishTypes();
 *           XRepository X();
 *           }
 *           }
 *
 *           package persistence.impl.jpa
 *           {
 *           class JpaRepositoryFactory
 *           class JpaDishTypeRepository
 *           class JpaXRepository
 *           }
 *
 *           package persistence.impl.inmemory
 *           {
 *           class InMemoryRepositoryFactory
 *           class InMemoryDishTypeRepository
 *           class InMemoryXRepository
 *           }
 *
 *           package core.dishmanagement.repositories
 *           {
 *           interface DishTypeRepository
 *           }
 *
 *           package core.Xmanagement.repositories
 *           {
 *           interface XRepository
 *           }
 *
 *           PersistenceContext .down.> RepositoryFactory
 *           PersistenceContext .down.> InMemoryRepositoryFactory:<<RunTime>>
 *           PersistenceContext .down.> JpaRepositoryFactory:<<RunTime>>
 *
 *           InMemoryRepositoryFactory ..|> RepositoryFactory
 *           JpaRepositoryFactory ..|> RepositoryFactory
 *
 *           DishTypeRepository <|.up. JpaDishTypeRepository
 *           DishTypeRepository <|.up. InMemoryDishTypeRepository
 *
 *           XRepository <|.up. JpaXRepository
 *           XRepository <|.up. InMemoryXRepository
 *
 *           RepositoryFactory ..> DishTypeRepository
 *           RepositoryFactory .down.> XRepository
 *
 *           InMemoryRepositoryFactory .down.> InMemoryDishTypeRepository:creates
 *           InMemoryRepositoryFactory .down.> InMemoryXRepository:creates
 *
 *           JpaRepositoryFactory .down.> JpaDishTypeRepository:creates
 *           JpaRepositoryFactory .down.> JpaXRepository:creates
 *
 * @enduml
 *         -->
 */
package eapli.helpdesk.infraestrutura;
