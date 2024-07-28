import './index.css'
import { createInertiaApp } from '@inertiajs/react'
import { createRoot } from 'react-dom/client'

createInertiaApp({
  id: 'root',
  resolve: name => {
    const pages = import.meta.glob('./**/*.jsx', { eager: true })
    return pages[`./${name}.jsx`]
  },
  setup({ el, App, props }) {
    createRoot(el).render(<App {...props} />)
  },
})
