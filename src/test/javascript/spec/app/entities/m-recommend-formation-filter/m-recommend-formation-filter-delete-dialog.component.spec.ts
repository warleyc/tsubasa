/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MRecommendFormationFilterDeleteDialogComponent } from 'app/entities/m-recommend-formation-filter/m-recommend-formation-filter-delete-dialog.component';
import { MRecommendFormationFilterService } from 'app/entities/m-recommend-formation-filter/m-recommend-formation-filter.service';

describe('Component Tests', () => {
  describe('MRecommendFormationFilter Management Delete Component', () => {
    let comp: MRecommendFormationFilterDeleteDialogComponent;
    let fixture: ComponentFixture<MRecommendFormationFilterDeleteDialogComponent>;
    let service: MRecommendFormationFilterService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRecommendFormationFilterDeleteDialogComponent]
      })
        .overrideTemplate(MRecommendFormationFilterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRecommendFormationFilterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRecommendFormationFilterService);
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
