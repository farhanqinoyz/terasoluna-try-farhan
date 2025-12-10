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

  const newToastElement = container.lastElementChild;

  const toastBootstrap = new bootstrap.Toast(newToastElement, {
    delay: 7000,
    animation: true
  });

  toastBootstrap.show();

  newToastElement.addEventListener('hidden.bs.toast', () => {
    newToastElement.remove();
  });
}