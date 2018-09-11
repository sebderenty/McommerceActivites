package com.mcommerce.microserviceexpedition.web.controller;

import com.mcommerce.microserviceexpedition.dao.ExpeditionDao;
import com.mcommerce.microserviceexpedition.model.Expedition;
import com.mcommerce.microserviceexpedition.web.exceptions.ExpeditionNotFoundException;
import com.mcommerce.microserviceexpedition.web.exceptions.ImpossibleAjouterExpeditionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    ExpeditionDao expeditionDao;

    /*
     * Création d'une expédition
     */
    @PostMapping(value = "/expeditions")
    public ResponseEntity<Expedition> ajouterCommande(@RequestBody Expedition expedition){

        Expedition nouvelleExpedition = expeditionDao.save(expedition);

        if(nouvelleExpedition == null) throw new ImpossibleAjouterExpeditionException("Impossible d'ajouter cette expedition");

        return new ResponseEntity<Expedition>(expedition, HttpStatus.CREATED);
    }

    /*
     * Récupération d'une expédition par son id
     */
    @GetMapping(value = "/expeditions/{id}")
    public Optional<Expedition> recupererUneExpedition(@PathVariable int id){

        Optional<Expedition> expedition = expeditionDao.findById(id);

        if(!expedition.isPresent()) throw new ExpeditionNotFoundException("Cette commande n'existe pas");

        return expedition;
    }

    /*
     * Mise à jour d'une expédition (uniquement les champs renseignés)
     */
    @PutMapping(value = "/expeditions")
    public void updateExpedition(@RequestBody Expedition expedition) {

        expeditionDao.save(expedition);
    }

}
