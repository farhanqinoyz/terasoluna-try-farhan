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
    if (!response.ok) throw new Error('Network error');
    return response.json();
  })
  .then(data => {
    showToast("Success adding item to cart", "success");
  })
  .catch((error) => {
    showToast("Error adding item! Please Try Again", "error");
  });
}

function showToast(message, type) {
  const container = document.getElementById('toastContainer');

  let bgClass = "text-bg-primary";
  if (type === 'success') bgClass = "text-bg-success";
  if (type === 'error') bgClass = "text-bg-danger";

  const toastHTML = `
                <div class="toast ${bgClass}" role="alert" aria-live="assertive" aria-atomic="true">
                    <div class="toast-header">
                        <strong class="me-auto">Notification</strong>
                        <small>Just now</small>
                        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                    <div class="toast-body">
                        ${message}
                    </div>
                </div>
            `;

  container.insertAdjacentHTML('beforeend', toastHTML);

  // 4. Get the DOM element we just created (it's the last child of the container)
  const newToastElement = container.lastElementChild;

  // 5. Initialize Bootstrap Toast on this specific element
  const toastBootstrap = new bootstrap.Toast(newToastElement, {
    delay: 7000, // 7 seconds
    animation: true
  });

  // 6. Show it
  toastBootstrap.show();

  // 7. CLEANUP: Important!
  // When the toast finishes hiding, remove it from the DOM completely.
  // Otherwise, your page will get full of invisible empty divs.
  newToastElement.addEventListener('hidden.bs.toast', () => {
    newToastElement.remove();
  });
}