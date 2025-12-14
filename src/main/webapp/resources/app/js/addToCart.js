function addToCart(id) {
  const baseUrl = contextPath ? contextPath : "";

  const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
  const csrfHeaderMeta = document.querySelector('meta[name="_csrf_header"]');

  const csrfToken = csrfTokenMeta ? csrfTokenMeta.getAttribute('content') : '';
  const csrfHeader = csrfHeaderMeta ? csrfHeaderMeta.getAttribute('content') : 'X-CSRF-TOKEN';

  fetch(baseUrl + '/cart/add-to-cart/' + id, {
    method: 'POST',
    headers: {
      [csrfHeader]: csrfToken,
      'Content-Type': 'application/json'
    }
  })
  .then(response => {
    if (!response.ok) {
      //TODO: receive error message from backend
      throw new Error('Network error');
    }
    return response.json();
  })
  .then(data => {
    showToast("Success adding item to cart", "success");
  })
  .catch((error) => {
    console.log(error);
    showToast("Error adding item: "+error+"\nPlease Try Again", "error");
  });
}