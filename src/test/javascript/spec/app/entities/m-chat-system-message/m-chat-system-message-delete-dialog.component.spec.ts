/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MChatSystemMessageDeleteDialogComponent } from 'app/entities/m-chat-system-message/m-chat-system-message-delete-dialog.component';
import { MChatSystemMessageService } from 'app/entities/m-chat-system-message/m-chat-system-message.service';

describe('Component Tests', () => {
  describe('MChatSystemMessage Management Delete Component', () => {
    let comp: MChatSystemMessageDeleteDialogComponent;
    let fixture: ComponentFixture<MChatSystemMessageDeleteDialogComponent>;
    let service: MChatSystemMessageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChatSystemMessageDeleteDialogComponent]
      })
        .overrideTemplate(MChatSystemMessageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChatSystemMessageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChatSystemMessageService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
