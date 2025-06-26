import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-custom-button',
  imports: [CommonModule],
  templateUrl: './custom-button.component.html',
  styleUrl: './custom-button.component.css'
})
export class CustomButtonComponent {
  @Input() text: string = 'app-custom-button'
  @Input() color: string = 'var(--primary-color)'
  @Input() textColor: string = 'var(--light-color)'
  @Output() buttonClick = new EventEmitter<void>()

  acction() {
    this.buttonClick.emit()
  }
}
