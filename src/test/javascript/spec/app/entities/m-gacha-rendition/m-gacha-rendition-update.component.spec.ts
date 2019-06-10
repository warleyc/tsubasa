/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionUpdateComponent } from 'app/entities/m-gacha-rendition/m-gacha-rendition-update.component';
import { MGachaRenditionService } from 'app/entities/m-gacha-rendition/m-gacha-rendition.service';
import { MGachaRendition } from 'app/shared/model/m-gacha-rendition.model';

describe('Component Tests', () => {
  describe('MGachaRendition Management Update Component', () => {
    let comp: MGachaRenditionUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionUpdateComponent>;
    let service: MGachaRenditionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRendition(123);
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
        const entity = new MGachaRendition();
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
