function pay() {
  const baseUrl = contextPath ? contextPath : "";

  const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
  const csrfHeaderMeta = document.querySelector('meta[name="_csrf_header"]');

  const csrfToken = csrfTokenMeta ? csrfTokenMeta.getAttribute('content') : '';
  const csrfHeader = csrfHeaderMeta ? csrfHeaderMeta.getAttribute('content') : 'X-CSRF-TOKEN';

  fetch(baseUrl + '/cart/payment/', {
    method: 'POST',
    headers: {
      [csrfHeader]: csrfToken,
      'Content-Type': 'application/json'
    }
  })
  .then(response => {
    if (!response.ok) throw new Error('Network error');
    return response;
  })
  .then(data => {
    showToast("Success to proceed payment", "success");
  })
  .catch((error) => {
    showToast("Error occurs! Please Try Again" + error, "error");
  });
}