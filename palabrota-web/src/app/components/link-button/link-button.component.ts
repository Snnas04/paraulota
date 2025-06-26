import { Component, Input, Output, EventEmitter } from '@angular/core'

@Component({
  selector: 'app-link-button',
  imports: [],
  templateUrl: './link-button.component.html',
  styleUrl: './link-button.component.css',
})
export class LinkButtonComponent {
  @Input() text: string = 'app-button'
  @Output() linkClick = new EventEmitter<string>()

  onClick() {
    this.linkClick.emit(this.text)
  }
}
