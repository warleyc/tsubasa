/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCutActionDeleteDialogComponent } from 'app/entities/m-cut-action/m-cut-action-delete-dialog.component';
import { MCutActionService } from 'app/entities/m-cut-action/m-cut-action.service';

describe('Component Tests', () => {
  describe('MCutAction Management Delete Component', () => {
    let comp: MCutActionDeleteDialogComponent;
    let fixture: ComponentFixture<MCutActionDeleteDialogComponent>;
    let service: MCutActionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutActionDeleteDialogComponent]
      })
        .overrideTemplate(MCutActionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCutActionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCutActionService);
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
