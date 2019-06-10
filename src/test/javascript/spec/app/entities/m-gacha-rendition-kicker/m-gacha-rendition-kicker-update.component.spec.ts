/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionKickerUpdateComponent } from 'app/entities/m-gacha-rendition-kicker/m-gacha-rendition-kicker-update.component';
import { MGachaRenditionKickerService } from 'app/entities/m-gacha-rendition-kicker/m-gacha-rendition-kicker.service';
import { MGachaRenditionKicker } from 'app/shared/model/m-gacha-rendition-kicker.model';

describe('Component Tests', () => {
  describe('MGachaRenditionKicker Management Update Component', () => {
    let comp: MGachaRenditionKickerUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionKickerUpdateComponent>;
    let service: MGachaRenditionKickerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionKickerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionKickerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionKickerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionKickerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionKicker(123);
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
        const entity = new MGachaRenditionKicker();
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
