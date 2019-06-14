/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformBottomDeleteDialogComponent } from 'app/entities/m-uniform-bottom/m-uniform-bottom-delete-dialog.component';
import { MUniformBottomService } from 'app/entities/m-uniform-bottom/m-uniform-bottom.service';

describe('Component Tests', () => {
  describe('MUniformBottom Management Delete Component', () => {
    let comp: MUniformBottomDeleteDialogComponent;
    let fixture: ComponentFixture<MUniformBottomDeleteDialogComponent>;
    let service: MUniformBottomService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformBottomDeleteDialogComponent]
      })
        .overrideTemplate(MUniformBottomDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MUniformBottomDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MUniformBottomService);
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
