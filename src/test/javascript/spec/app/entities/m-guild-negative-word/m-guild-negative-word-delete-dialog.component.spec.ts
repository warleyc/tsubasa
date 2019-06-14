/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildNegativeWordDeleteDialogComponent } from 'app/entities/m-guild-negative-word/m-guild-negative-word-delete-dialog.component';
import { MGuildNegativeWordService } from 'app/entities/m-guild-negative-word/m-guild-negative-word.service';

describe('Component Tests', () => {
  describe('MGuildNegativeWord Management Delete Component', () => {
    let comp: MGuildNegativeWordDeleteDialogComponent;
    let fixture: ComponentFixture<MGuildNegativeWordDeleteDialogComponent>;
    let service: MGuildNegativeWordService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildNegativeWordDeleteDialogComponent]
      })
        .overrideTemplate(MGuildNegativeWordDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildNegativeWordDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildNegativeWordService);
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
