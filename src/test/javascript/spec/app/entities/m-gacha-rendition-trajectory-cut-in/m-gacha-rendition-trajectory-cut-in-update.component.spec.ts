/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTrajectoryCutInUpdateComponent } from 'app/entities/m-gacha-rendition-trajectory-cut-in/m-gacha-rendition-trajectory-cut-in-update.component';
import { MGachaRenditionTrajectoryCutInService } from 'app/entities/m-gacha-rendition-trajectory-cut-in/m-gacha-rendition-trajectory-cut-in.service';
import { MGachaRenditionTrajectoryCutIn } from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';

describe('Component Tests', () => {
  describe('MGachaRenditionTrajectoryCutIn Management Update Component', () => {
    let comp: MGachaRenditionTrajectoryCutInUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionTrajectoryCutInUpdateComponent>;
    let service: MGachaRenditionTrajectoryCutInService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTrajectoryCutInUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionTrajectoryCutInUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionTrajectoryCutInUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionTrajectoryCutInService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionTrajectoryCutIn(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionTrajectoryCutIn();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
