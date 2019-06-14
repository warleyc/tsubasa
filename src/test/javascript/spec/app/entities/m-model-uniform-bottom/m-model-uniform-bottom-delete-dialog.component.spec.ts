/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformBottomDeleteDialogComponent } from 'app/entities/m-model-uniform-bottom/m-model-uniform-bottom-delete-dialog.component';
import { MModelUniformBottomService } from 'app/entities/m-model-uniform-bottom/m-model-uniform-bottom.service';

describe('Component Tests', () => {
  describe('MModelUniformBottom Management Delete Component', () => {
    let comp: MModelUniformBottomDeleteDialogComponent;
    let fixture: ComponentFixture<MModelUniformBottomDeleteDialogComponent>;
    let service: MModelUniformBottomService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformBottomDeleteDialogComponent]
      })
        .overrideTemplate(MModelUniformBottomDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelUniformBottomDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelUniformBottomService);
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
