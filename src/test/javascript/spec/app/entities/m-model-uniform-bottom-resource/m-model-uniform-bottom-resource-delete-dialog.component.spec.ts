/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformBottomResourceDeleteDialogComponent } from 'app/entities/m-model-uniform-bottom-resource/m-model-uniform-bottom-resource-delete-dialog.component';
import { MModelUniformBottomResourceService } from 'app/entities/m-model-uniform-bottom-resource/m-model-uniform-bottom-resource.service';

describe('Component Tests', () => {
  describe('MModelUniformBottomResource Management Delete Component', () => {
    let comp: MModelUniformBottomResourceDeleteDialogComponent;
    let fixture: ComponentFixture<MModelUniformBottomResourceDeleteDialogComponent>;
    let service: MModelUniformBottomResourceService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformBottomResourceDeleteDialogComponent]
      })
        .overrideTemplate(MModelUniformBottomResourceDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelUniformBottomResourceDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelUniformBottomResourceService);
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
