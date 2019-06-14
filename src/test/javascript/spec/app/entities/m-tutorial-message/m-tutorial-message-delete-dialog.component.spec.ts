/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTutorialMessageDeleteDialogComponent } from 'app/entities/m-tutorial-message/m-tutorial-message-delete-dialog.component';
import { MTutorialMessageService } from 'app/entities/m-tutorial-message/m-tutorial-message.service';

describe('Component Tests', () => {
  describe('MTutorialMessage Management Delete Component', () => {
    let comp: MTutorialMessageDeleteDialogComponent;
    let fixture: ComponentFixture<MTutorialMessageDeleteDialogComponent>;
    let service: MTutorialMessageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTutorialMessageDeleteDialogComponent]
      })
        .overrideTemplate(MTutorialMessageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTutorialMessageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTutorialMessageService);
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
