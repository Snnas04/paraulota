import { Component, EventEmitter, Input, Output } from '@angular/core'

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrl: './button.component.css',
})
export class ButtonComponent {
  @Input() text: string = 'app-button'
  @Output() buttonClick = new EventEmitter<void>()

  acction() {
    this.buttonClick.emit()
  }
}
