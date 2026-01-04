const plugin = require('tailwindcss/plugin')

module.exports = {
  content: ['./templates/**/*.html'],
  theme: {
    extend: {
      colors: {
        primary: '#e6ebf5', // light mode background
        secondary: '#d3d9e6', // light mode footer
        darkprimary: '#23243b', // dark mode background
        darksecondary: '#2f314a', // dark mode footer
        bodytext: '#111827', // light mode text
        darkbodytext: '#f5f5f5', // dark mode text
        accent: '#9333ea', // light mode accent
        darkaccent: '#2563eb', // dark mode accent
        darkformbg: '#4B506E', // dark mode form background
      },
    },
  },
  plugins: [
    plugin(function ({ addUtilities }) {
      addUtilities({
        '.pressed': {
          borderRadius: '5px',
          filter: 'brightness(0.9)',
          background: '#2f314a',
        },
      })
    }),
  ],
}