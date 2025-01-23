const URL_API = 'http://localhost:8080/api/';

// let jsonExample = [
//   {
//     "id": 2,
//     "domain": "elpais.com",
//     "url": null,
//     "title": "Portada de EL PAÍS del 14-05-2024",
//     "description": "Repasa, descarga y comparte la portada de EL PAÍS del día 14-05-2024. Además las noticias de última hora sobre la actualidad en España y el mundo: política, economía, deportes, cultura, sociedad, tecnología, gente, opinión, viajes, moda, televisión, los blogs y las firmas de EL PAÍS. Además especiales, vídeos, fotos, audios, gráficos, entrevistas, promociones y todos los servicios de EL PAÍS.",
//     "picture": "",
//     "rank": null
//   },
//   {
//     "id": 3,
//     "domain": "elpais.com",
//     "url": "https://elpais.com/hemeroteca/elpais/portadas/2024/05/14/",
//     "title": "Portada de EL PAÍS del 14-05-2024",
//     "description": "Repasa, descarga y comparte la portada de EL PAÍS del día 14-05-2024. Además las noticias de última hora sobre la actualidad en España y el mundo: política, economía, deportes, cultura, sociedad, tecnología, gente, opinión, viajes, moda, televisión, los blogs y las firmas de EL PAÍS. Además especiales, vídeos, fotos, audios, gráficos, entrevistas, promociones y todos los servicios de EL PAÍS.",
//     "picture": "https://srv00.epimg.net/pdf/elpais/snapshot/2024/05/elpais/20240514.jpg",
//     "rank": null
//   }
// ]

function onClickSearch() {
  let query = document.getElementById("txtSearch").value;
  search(query);
}

async function search(query) {
  let url = URL_API + "search?query=" + query;

  let html = '';

  await fetch(url)
      .then(response => {
        if (!response.ok) {
          throw new Error('Error in request: ' + response.statusText);
        }
        return response.json();
      })
      .then(data => {
        data.forEach(webpage => {
            let description = webpage.description.substring(0, 200) + "...";

            html += `<div class="result">
                        <div class="title">
                          <img class="picture"
                            src="${webpage.picture}" />
                          <a href="${webpage.url}" target="_blank">${webpage.title}</a>
                        </div>
                        <div class="description">
                          <p>${description}</p>
                        </div>
                      </div>`;
        });
      })
      .catch(error => {
        console.error('There was a problem with the request:', error);
      });

  document.getElementById('results').innerHTML = html;
}

function load() {
  let query = window.location.href.split('?query=')[1];
  document.getElementById("txtSearch").value = query;
  search(query);
}

load();