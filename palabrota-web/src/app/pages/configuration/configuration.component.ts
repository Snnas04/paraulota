import { CommonModule } from '@angular/common'
import { Component } from '@angular/core'
import { BurgerMenuComponent } from '@components/burger-menu/burger-menu.component'
import { NavigationService } from '@services/navigation.service'
import { SettingsService } from '@services/settings.service'

@Component({
  selector: 'app-configuration',
  imports: [BurgerMenuComponent, CommonModule],
  templateUrl: './configuration.component.html',
  styleUrl: './configuration.component.css',
})
export class ConfigurationComponent {
  general: boolean = true
  appearance: boolean = false
  rule: boolean = false
  gameMode: boolean = false
  features: boolean = false

  primaryColor: string =
    getComputedStyle(document.documentElement).getPropertyValue('--primary-color').trim() ||
    '#2d96ec'

  difficultyOptions = [
    { label: 'Muy facil', letters: 1, attempts: 2 },
    { label: 'Facil', letters: 1, attempts: 1 },
    { label: 'Normal', letters: 2, attempts: 2 },
    { label: 'Dificil', letters: 2, attempts: 1 },
    { label: 'Muy dificil', letters: 3, attempts: 2 },
    { label: 'Extremo', letters: 3, attempts: 1 },
  ]
  difficultyLabels: string[] = this.difficultyOptions.map((o) => o.label)

  constructor(
    public settings: SettingsService,
    public navigation: NavigationService
  ) {
    const idx = this.difficultyOptions.findIndex(
      (opt) => opt.letters === this.settings.letters && opt.attempts === this.settings.attempts
    )
    if (idx === -1) {
      this.settings.letters = 2
      this.settings.attempts = 2
    }
  }

  get selectedDifficulty() {
    const idx = this.difficultyOptions.findIndex(
      (opt) => opt.letters === this.settings.letters && opt.attempts === this.settings.attempts
    )
    return this.difficultyOptions[idx !== -1 ? idx : 0]
  }

  private darkenColor(hex: string, percent: number): string {
    // Quita el # si está presente
    hex = hex.replace(/^#/, '')
    // Convierte a RGB
    let r = parseInt(hex.substring(0, 2), 16)
    let g = parseInt(hex.substring(2, 4), 16)
    let b = parseInt(hex.substring(4, 6), 16)

    r = Math.floor(r * (1 - percent))
    g = Math.floor(g * (1 - percent))
    b = Math.floor(b * (1 - percent))

    // Asegura dos dígitos hexadecimales
    return '#' + [r, g, b].map((x) => x.toString(16).padStart(2, '0')).join('')
  }

  onDifficultyChange(selectedLabel: string) {
    const opt = this.difficultyOptions.find((o) => o.label === selectedLabel)
    if (opt) {
      this.settings.letters = opt.letters
      this.settings.attempts = opt.attempts
    }
  }

  onPrimaryColorChange(event: Event) {
    const color = (event.target as HTMLInputElement).value
    document.documentElement.style.setProperty('--primary-color', color)
    this.primaryColor = color
    // Calcula y aplica el hover más oscuro (por ejemplo, 20% más oscuro)
    const hoverColor = this.darkenColor(color, 0.2)
    document.documentElement.style.setProperty('--primary-hover-color', hoverColor)
    localStorage.setItem('primary-color', color)
    localStorage.setItem('primary-hover-color', hoverColor)
  }

  onResetPrimaryColor() {
    this.primaryColor = '#2d96ec'
    document.documentElement.style.setProperty('--primary-color', this.primaryColor)
    document.documentElement.style.setProperty('--primary-hover-color', '#2d79ec')
    localStorage.removeItem('primary-color')
    localStorage.removeItem('primary-hover-color')
  }

  ngOnInit() {
    const savedColor = localStorage.getItem('primary-color')
    if (savedColor) {
      document.documentElement.style.setProperty('--primary-color', savedColor)
      this.primaryColor = savedColor
      const hoverColor =
        localStorage.getItem('primary-hover-color') || this.darkenColor(savedColor, 0.2)
      document.documentElement.style.setProperty('--primary-hover-color', hoverColor)
    }
  }
}
