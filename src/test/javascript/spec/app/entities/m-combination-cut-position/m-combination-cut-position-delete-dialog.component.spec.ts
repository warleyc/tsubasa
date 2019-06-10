/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCombinationCutPositionDeleteDialogComponent } from 'app/entities/m-combination-cut-position/m-combination-cut-position-delete-dialog.component';
import { MCombinationCutPositionService } from 'app/entities/m-combination-cut-position/m-combination-cut-position.service';

describe('Component Tests', () => {
  describe('MCombinationCutPosition Management Delete Component', () => {
    let comp: MCombinationCutPositionDeleteDialogComponent;
    let fixture: ComponentFixture<MCombinationCutPositionDeleteDialogComponent>;
    let service: MCombinationCutPositionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCombinationCutPositionDeleteDialogComponent]
      })
        .overrideTemplate(MCombinationCutPositionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCombinationCutPositionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCombinationCutPositionService);
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
