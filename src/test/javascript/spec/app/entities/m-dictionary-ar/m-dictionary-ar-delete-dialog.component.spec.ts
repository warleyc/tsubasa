/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryArDeleteDialogComponent } from 'app/entities/m-dictionary-ar/m-dictionary-ar-delete-dialog.component';
import { MDictionaryArService } from 'app/entities/m-dictionary-ar/m-dictionary-ar.service';

describe('Component Tests', () => {
  describe('MDictionaryAr Management Delete Component', () => {
    let comp: MDictionaryArDeleteDialogComponent;
    let fixture: ComponentFixture<MDictionaryArDeleteDialogComponent>;
    let service: MDictionaryArService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryArDeleteDialogComponent]
      })
        .overrideTemplate(MDictionaryArDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryArDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryArService);
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
