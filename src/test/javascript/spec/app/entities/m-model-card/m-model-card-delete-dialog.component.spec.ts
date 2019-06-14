/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MModelCardDeleteDialogComponent } from 'app/entities/m-model-card/m-model-card-delete-dialog.component';
import { MModelCardService } from 'app/entities/m-model-card/m-model-card.service';

describe('Component Tests', () => {
  describe('MModelCard Management Delete Component', () => {
    let comp: MModelCardDeleteDialogComponent;
    let fixture: ComponentFixture<MModelCardDeleteDialogComponent>;
    let service: MModelCardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelCardDeleteDialogComponent]
      })
        .overrideTemplate(MModelCardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelCardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelCardService);
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
