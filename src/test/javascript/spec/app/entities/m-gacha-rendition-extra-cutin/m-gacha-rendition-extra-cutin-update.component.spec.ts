/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionExtraCutinUpdateComponent } from 'app/entities/m-gacha-rendition-extra-cutin/m-gacha-rendition-extra-cutin-update.component';
import { MGachaRenditionExtraCutinService } from 'app/entities/m-gacha-rendition-extra-cutin/m-gacha-rendition-extra-cutin.service';
import { MGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';

describe('Component Tests', () => {
  describe('MGachaRenditionExtraCutin Management Update Component', () => {
    let comp: MGachaRenditionExtraCutinUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionExtraCutinUpdateComponent>;
    let service: MGachaRenditionExtraCutinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionExtraCutinUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionExtraCutinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionExtraCutinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionExtraCutinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionExtraCutin(123);
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
        const entity = new MGachaRenditionExtraCutin();
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
