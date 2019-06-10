/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionBeforeShootCutInPatternDetailComponent } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-pattern/m-gacha-rendition-before-shoot-cut-in-pattern-detail.component';
import { MGachaRenditionBeforeShootCutInPattern } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';

describe('Component Tests', () => {
  describe('MGachaRenditionBeforeShootCutInPattern Management Detail Component', () => {
    let comp: MGachaRenditionBeforeShootCutInPatternDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionBeforeShootCutInPatternDetailComponent>;
    const route = ({
      data: of({ mGachaRenditionBeforeShootCutInPattern: new MGachaRenditionBeforeShootCutInPattern(123) })
    } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionBeforeShootCutInPatternDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionBeforeShootCutInPatternDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionBeforeShootCutInPatternDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionBeforeShootCutInPattern).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
