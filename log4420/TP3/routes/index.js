var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Accueil', selected: 'accueil' });
});

router.get('/tableauDeBord', function(req, res, next) {
  res.render('tableauDeBord', { title: 'Tableau De Bord', selected: 'tableauDeBord' });
});

router.get('/instruction', function(req, res, next) {
  res.render('instruction', { title: 'Instruction', selected: 'instruction' });
});

router.get('/testRapide/:page', function(req, res, next) {
  if (req.params.page == 1) {
    res.render('testRapidePage1', {title: 'Test Rapide'});
  }
  else if (req.params.page == 2){
    res.render('testRapidePage2', {title: 'Test Rapide'});
  }
  else {
     res.redirect('/tableauDeBord');
  }
});

router.get('/examen/:page', function(req, res, next) {
  if (req.params.page == 1) {
    res.render('examenPage1', {title: 'Examen'});
  }
  else if (req.params.page == 2){
    res.render('examenPage2', {title: 'Examen'});
  }
  else {
     res.redirect('/tableauDeBord');
  }
});

router.get('/resultat', function(req, res, next) {
  res.render('resultat', { title: 'Resultat' });
});

router.get('/instruction', function(req, res, next) {
  res.render('instruction');
});

module.exports = router;
