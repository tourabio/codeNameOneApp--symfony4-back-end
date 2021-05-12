function search(path , value )
{
    $.ajax({
        beforeSend: function(){
            $('.ajax-loader').css("visibility", "visible");
        },
        /* l’url est une chaine de caractères contenant l’adresse où la requête est envoyée */
        url : path ,
        /* La méthode utilisée pour transférer les données est GET */
        type : 'GET',
        /*Ici search value va prendre la chaine entrée par un utilisateur dans la zone de recherche et sera placée après l’url */
        data: {
            'search_input' : value ,
        },
        /*Cette fonction permet de vider le contenu du tableau pour recevoir le nouveau contenu*/
        success : function(retour) {
            // si recherche trouve au moin un element
            if (retour) {
                // vider tableau
                $('#tableId').empty();
                // boucle sur liste
                $.each(JSON.parse(retour), function (i, obj) {
                    var reclamation = obj ;
                    $('#tableId').append("<li class=\"d-md-table mb-4 w-100 border-bottom hover-shadow\">" +
                        "                                <div class=\"d-md-table-cell text-center p-4 bg-primary text-white mb-4 mb-md-0\" style=\"width: 100px;\"><span class=\"h2 d-block\">" + getDay(reclamation.date)  + " </span>"+ formatDate(reclamation.date) + "</div>" +
                        "                                <div class=\"d-md-table-cell px-4 vertical-align-middle mb-4 mb-md-0\">\n" +
                        "                                    <p href=\"\" class=\"h4 mb-3 d-block\">" + reclamation.sujet + "</p>\n" +
                        "                                    <p class=\"mb-0\">" + reclamation.comment + "</p>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"d-md-table-cell text-right pr-0 pr-md-4\" ><p href=\"\" class=\"btn btn-primary\" style=\"width: 200px;\">"+ reclamation.etat +"</p></div>\n" +
                        "                            </li>");

                });
            } else {
                // si recherche ne trouve rien
                $('#tableId').empty();
            }
        },
        complete: function(){
            $('.ajax-loader').css("visibility", "hidden");
        }
    });
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month].join('-');
}

function getDay(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;
    return  day;
}