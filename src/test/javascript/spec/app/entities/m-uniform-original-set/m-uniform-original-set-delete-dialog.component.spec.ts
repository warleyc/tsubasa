/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformOriginalSetDeleteDialogComponent } from 'app/entities/m-uniform-original-set/m-uniform-original-set-delete-dialog.component';
import { MUniformOriginalSetService } from 'app/entities/m-uniform-original-set/m-uniform-original-set.service';

describe('Component Tests', () => {
  describe('MUniformOriginalSet Management Delete Component', () => {
    let comp: MUniformOriginalSetDeleteDialogComponent;
    let fixture: ComponentFixture<MUniformOriginalSetDeleteDialogComponent>;
    let service: MUniformOriginalSetService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformOriginalSetDeleteDialogComponent]
      })
        .overrideTemplate(MUniformOriginalSetDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MUniformOriginalSetDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MUniformOriginalSetService);
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
