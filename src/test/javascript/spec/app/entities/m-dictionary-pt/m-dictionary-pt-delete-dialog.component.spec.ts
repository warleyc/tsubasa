/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryPtDeleteDialogComponent } from 'app/entities/m-dictionary-pt/m-dictionary-pt-delete-dialog.component';
import { MDictionaryPtService } from 'app/entities/m-dictionary-pt/m-dictionary-pt.service';

describe('Component Tests', () => {
  describe('MDictionaryPt Management Delete Component', () => {
    let comp: MDictionaryPtDeleteDialogComponent;
    let fixture: ComponentFixture<MDictionaryPtDeleteDialogComponent>;
    let service: MDictionaryPtService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryPtDeleteDialogComponent]
      })
        .overrideTemplate(MDictionaryPtDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryPtDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryPtService);
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
