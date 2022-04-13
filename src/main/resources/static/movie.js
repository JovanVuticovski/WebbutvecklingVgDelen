
//Skapar handleDelete metod
// Gör anrop med fetch till webb-adressen /movie + id på movieRating inlägget
// initierar en metod för Delete på den valda movieRating med dess id

const handleDelete = id => fetch('/blog/' + id, {method: 'DELETE'})


    // .then körs direkt efter anropet med fetch körts
    // Går till url där anropet gjorts, d.v.s behåll samma webbsida som delete anropet gjorts på.
    .then(res => window.location.href = res.url)
